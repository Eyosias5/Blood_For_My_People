package et.edu.aait.roomdbexample.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teambloodformypeople.models.User

@Dao
interface UserDao {

    @Query("SELECT * from users ORDER BY user_id")
    fun getAllUsers(): LiveData<List<User>>


}

