package et.edu.aait.roomdbexample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teambloodformypeople.data.models.User


@Dao
interface UserDao {

    @Query("SELECT * from users ORDER BY userId")
    fun getAllUsers(): LiveData<List<User>>


}

