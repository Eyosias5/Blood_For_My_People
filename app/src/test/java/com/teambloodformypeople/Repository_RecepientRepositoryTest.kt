package com.teambloodformypeople

public class Repository_RecepientRepositoryTest {
//    @get:Rule
//    val testRule = InstantTaskExecutorRule()
//
//    private lateinit var recepientRepository: RecepientRepository
//
//    @Before
//    fun before(){
//        recepientRepository = RecepientRepository(RecepientApiService.getInstance(),DB.getDatabase(ApplicationProvider.getApplicationContext()).recipientDao(),Application())
//    }
//
//    // INSERT RECEPIEN
//    @Test
//    fun insertUserTest() {
//        var recepient = TemporaryRecepientHolder(
//            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",dateOfBirth = ""
//        )
//        GlobalScope.launch {
//            recepientRepository.insertRecepientAsync(recepient)
//            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
//            if (recepientFetched==null){
//                assert(false)
//            }
//            else if(recepientFetched.fullName==recepient.name){
//                assert(true)
//            }
//            assert(false)
//        }
//    }
//
//    // GET DONOR
//    @Test
//    fun getUserTest() {
//        var recepient = TemporaryRecepientHolder(
//            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",dateOfBirth = ""
//        )
//        GlobalScope.launch {
//            recepientRepository.insertRecepientAsync(recepient)
//            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
//            if (recepientFetched==null){
//                assert(false)
//            }
//            else if(recepientFetched.fullName==recepient.name){
//                assert(true)
//            }
//            assert(false)
//        }
//    }
//
//    //DELETE DONOR
//    @Test
//    fun deleteUserTest() {
//        var recepient = TemporaryRecepientHolder(
//            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",dateOfBirth = ""
//        )
//        GlobalScope.launch {
//            recepientRepository.insertRecepientAsync(recepient)
//            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
//            if (recepientFetched==null){
//                assert(false)
//            }
//            else if(recepientFetched.fullName==recepient.name){
//                recepientFetched.recepientId.let { recepientRepository.deleteRecepientAsync(it) }
//                assert(true)
//            }
//            assert(false)
//        }
//    }
//
//    //UPDATE DONOR
//    @Test
//    fun updateUserTest() {
//        var recepient = TemporaryRecepientHolder(
//            password = "123456", username = "a@a.com", name = "recepient",phone = "0912345678",dateOfBirth = ""
//        )
//        GlobalScope.launch {
//            recepientRepository.insertRecepientAsync(recepient)
//            var recepientFetched = recepientRepository.findRecepientByUserIdAsync(1).body()
//            if (recepientFetched==null){
//                assert(false)
//            }
//            else if(recepientFetched.fullName==recepient.name){
//                recepientFetched.fullName="bb"
//                recepientRepository.updateRecepientAsync(recepientFetched)
//                assert(true)
//            }
//            assert(false)
//        }
//    }

}