package com.example.macaronagaintoay.Database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.macaronagaintoay.Dao.CafeDao
import com.example.macaronagaintoay.Dao.DBVersionDao
import com.example.macaronagaintoay.Dao.SearchDao
import com.example.macaronagaintoay.Entity.CafeEntity
import com.example.macaronagaintoay.Entity.DBVersionEntity
import com.example.macaronagaintoay.Entity.SearchEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(
    entities = [SearchEntity::class, CafeEntity::class, DBVersionEntity::class],
    version = 8,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){  //RoomDatabase는 추상클래스로 이루어져야함 / 싱글톤으로 구현되어야함

    abstract fun searchDao() : SearchDao
    abstract fun cafeDao() : CafeDao
    abstract fun DBVersionDao() : DBVersionDao

    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        private const val DATABASE_NAME = "app_database"

        fun getDatabase(
            context: Context,
            scope:CoroutineScope

        ) : AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()  //insert는 데이터 추가이므로 coroutine 사용
                    .build()
                INSTANCE = instance
                INSTANCE?.updateDatabaseCreated(context.applicationContext)

                instance
            }
        }

    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {

                    it.DBVersionDao().insert(DBVersionEntity(null,0))

            }


        }

        override fun onOpen(db: SupportSQLiteDatabase) {  //데이터 베이스가 열릴때마다 할 활동
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.searchDao())
                }
            }
        }

        suspend fun populateDatabase(searchDao: SearchDao){

        }




    }


    private fun updateDatabaseCreated(context: Context){
        if(context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true)
    }

    open fun getDatabaseCreated():LiveData<Boolean>{  //생성된 데이터베이스를 가져오는 메소드
        return mIsDatabaseCreated
    }






}