package com.example.macaronagaintoay.Database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.macaronagaintoay.Api.PostApi
import com.example.macaronagaintoay.Api.Retrofit
import com.example.macaronagaintoay.entity.CafeEntity
import com.example.macaronagaintoay.entity.DBVersionEntity
import com.example.macaronagaintoay.entity.SearchEntity
import com.example.macaronagaintoay.dataclass.MyAllCafeList
import com.example.macaronagaintoay.dataclass.MyDBversion
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(mDatabase : AppDatabase) {

    private val SearchDao = mDatabase.searchDao()
    private val CafeDao = mDatabase.cafeDao()
    private val DBVersionDao = mDatabase.DBVersionDao()
    val allSearch : LiveData<List<SearchEntity>> = SearchDao.getAlphabetizedSearch()
    val _allCafe : LiveData<List<CafeEntity>> = CafeDao.getAll()
    val allCafe : LiveData<List<CafeEntity>>
        get() = _allCafe

//    val CafeList : List<CafeEntity> = CafeDao.getCafeList()
//    val  CafeList : List<CafeEntity>
//        get() = _CafeList

    val LastDbVersion : LiveData<DBVersionEntity> = DBVersionDao.getVersion()

    var retrofit = Retrofit().getClient()
    var _getDbversion = retrofit!!.create(PostApi::class.java)
    val getDBversion
        get()= _getDbversion

    var _getCafelist = retrofit!!.create(PostApi::class.java)
    val getCafeList get() = _getCafelist

    var _DBversionState = MutableLiveData<Boolean>()
    val DBversionState : LiveData<Boolean> get() = _DBversionState

    companion object{
        private var sInstance : Repository? = null

        fun getInstance(database: AppDatabase) : Repository{
            return  sInstance
                ?: synchronized(this){
                    val instance = Repository(database)
                    sInstance = Repository(database)
                    instance
                }
        }
    }

    suspend fun insert(searchEntity: SearchEntity){
        SearchDao.insert(searchEntity)
    }

    suspend fun CafeInsert(cafeEntity: CafeEntity){
        CafeDao.insert(cafeEntity)
    }

    //DB버전 인서트
    suspend fun DBVersionInsert(DBVersionEntity : DBVersionEntity){
        DBVersionDao.insert(DBVersionEntity)
    }

    suspend fun  delete(){
        SearchDao.deleteAll()
    }

    fun deletes(searchEntity: SearchEntity){
        SearchDao.deletes(searchEntity)
    }

    suspend fun cafeDeleteAll(){
        Log.d("asdasd cafedelete", "카페 리스트 삭제")
        CafeDao.deleteAll()
    }

    //DB버전 삭제
    suspend fun DBversionDelete(){
        DBVersionDao.deleteAll()
    }

    suspend fun getDBversion(version : Int){

        getDBversion.getDBVersion().enqueue(object : Callback<MyDBversion>{
            override fun onResponse(call: Call<MyDBversion>, response: Response<MyDBversion>) {
                var data = response.body()!!.getDBversion()

                Log.d("asd response", data[0].version.toString())

                if(version < data[0].version!!){
                    Log.d("asdasd ip t", "DB버전이 크다")

                    GlobalScope.launch {
                        cafeDeleteAll()
                        getCafelist(data[0].version!!)
                    }

                }else if(version == data[0].version!!!!){

                    _DBversionState.postValue(true)
                    Log.d("asdasd ip t", "DB버전이 같다")

                } else{

                    Log.d("asdasd ip t", "DB버전이 작다")

                }

            }

            override fun onFailure(call: Call<MyDBversion>, t: Throwable) {
                Log.d("asd failuer",  t.toString())
            }

        })

    }

    suspend fun getDBversion(){

        getDBversion.getDBVersion().enqueue(object : Callback<MyDBversion>{
            override fun onResponse(call: Call<MyDBversion>, response: Response<MyDBversion>) {
                var data = response.body()!!.getDBversion()

                Log.d("asd response", data[0].version.toString())


                GlobalScope.launch {
                    cafeDeleteAll()
                    getCafelist(data[0].version!!)

                }


            }

            override fun onFailure(call: Call<MyDBversion>, t: Throwable) {
                Log.d("asd failuer",  t.toString())
            }

        })

    }


    suspend fun getCafelist(version: Int) {

        getCafeList.getAllCafelist().enqueue(object: Callback<MyAllCafeList>{
            override fun onResponse(
                call: Call<MyAllCafeList>,
                response: Response<MyAllCafeList>
            ) {

                Log.d("asdasd getcafe", response.body()!!.getCafeList().toString())

                var data = response.body()!!.getCafeList()

                CoroutineScope(IO).launch {
                    for(i in data.indices){
                        CafeInsert(CafeEntity(data[i].id,data[i].cafeName!!,data[i].category!!, data[i].newAddress!!, data[i].openTime!!, data[i].telNumber!!, data[i].city!!, data[i].oldAddress!!, data[i].lat.toString(), data[i].lng.toString()))
                    }
                    DBVersionDao.insert(DBVersionEntity(null, version))
                }


            }

            override fun onFailure(call: Call<MyAllCafeList>, t: Throwable) {
                Log.d("asdasd getcafe ", t.toString())
            }

        })


    }






}