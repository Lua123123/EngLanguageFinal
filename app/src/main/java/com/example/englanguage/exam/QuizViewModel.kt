package com.example.englanguage.exam

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englanguage.database.VocabularyDatabase
import com.example.englanguage.model.exam.Question
import kotlinx.coroutines.*

class QuizViewModel(var context: Context) : ViewModel() {
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private lateinit var job: Job

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
    fun addDataContract() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(v) tôn trọng, tuân theo, giữ (lời)?", "Abide by", "Assurance", "Accommodate", "Address", 1, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/467d01d88f618e1c7a98db36bdd93572_400_300.jpg"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(v) điều tiết, điều chỉnh, thu xếp, làm cho phù hợp?", "Address", "access", "Accommodate", "Abide by", 3, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/cd3a87a1346ca4b72c69f18a454be7d7_640_401.jpg"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xác định, định rõ?", "Determine", "access", "Accommodate", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Participate, involve: tham gia, tham dự\n" + "Engage in a contract: tham gia vào một hợp đồng?", "Address", "access", "Accommodate", "Engage", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(n) địa chỉ, diễn văn; (v) trình bày?", "Abide by", "Address", "Accommodate", "access", 2, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/08c6cb7a29958ae5ad8702edfc46132a_2144_1747.jpg"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(n) hợp đồng, giao kèo, sự đồng ý/thỏa thuận với nhau?", "Arrangement", "Assurance", "Association", "Agreement", 4, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/e414e1801ca8ccfacd470a714d80dd0e_264_191.jfif"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(n) ‹sự› thu xếp, dàn xếp, sắp xếp, sắp đặt?", "Arrangement", "Agreement", "Cancellation", "Address", 1, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/cb13b4c272fa1e18b51c930b17248f53_2776_2776.jpg"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(n) hội, hội liên hiệp, đoàn thể; ‹sự› kết hợp, liên kết, liên hợp?", "access", "Association", "Accommodate", "Demonstrate", 2, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/39a4abda473087aa4605d294c19a6c78_626_417.jpg"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(n) sự cam đoan, bảo đảm, chắc chắn?", "Assurance", "Specific", "Accommodate", "Substitution", 1, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/e15667d5ea95f41774a02a96566b8a7d_309_163.jfif"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(v) tham dự, có mặt; chăm sóc, phục vụ; đi theo, đi kèm, hộ tống?", "Accommodate", "Association", "Attend", "Address", 3, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/39df82b4c4020595073d4f20e152d46b_640_427.jpg"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(v) Hap dan, loi cuon?", "Currently", "Consume", "Accommodate", "Attract", 4, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/925f21187add4d9baa23aa063b633600_825_510.png"))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("(v) tránh, tránh khỏi; hủy bỏ, bác bỏ?", "Consequence", "Characteristic", "Accommodate", "Avoid", 4, "https://mybiz-div.s3.ap-southeast-1.amazonaws.com/product/199bda1cc315a32393e5c835d67c68a8_600_400.jpg"))
        }
    }

    fun addDataMarketing() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hấp dẫn, lôi cuốn?", "Attract", "Assurance", "Accommodate", "Address", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("So sánh?", "Address", "access", "Compare", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự cạnh tranh?", "Abide by", "Competition", "Accommodate", "access", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("to absorb = to use up: tiêu dùng, sử dụng?", "Arrangement", "Assurance", "Association", "Consume", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Thuyết phục?", "Convince", "Agreement", "Cancellation", "Address", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hiện thời, hiện nay?", "access", "Currently", "Accommodate", "Demonstrate", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Mốt nhất thời?", "Fad", "Specific", "Accommodate", "Substitution", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truyền cảm hứng cho ai đó làm việc gì?", "Accommodate", "Association", "Inspire someone to do something", "Fad", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Thị trường?", "Currently", "Consume", "Accommodate", "Market - in the market", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự thuyết phục?", "Consequence", "Characteristic", "Accommodate", "Persuasion", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Năng suất?", "Consequence", "Characteristic", "Productive", "Persuasion", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự vừa long, thỏa mãn?", "Consequence", "Satisfaction", "Accommodate", "Persuasion", 2, ""))
        }
    }

    fun addDataWarranties() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Đặc tính, đặc trưng?", "Characteristic", "Assurance", "Accommodate", "Consequence", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Kết quả, hệ quả?", "Characteristic", "access", "Consequence", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cân nhắc, xem xét?", "Characteristic", "Consider", "Consequence", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bao gồm, bao phủ?", "Characteristic", "Consider", "Expiration", "Cover", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự hết hạn, kết thúc?", "Expiration", "Agreement", "Cancellation", "Address", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Thường xuyên?", "Characteristic", "Frequently", "Expiration", "Cover", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Ngụ ý, hàm ý?", "Imply", "Specific", "Accommodate", "Substitution", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hứa hẹn, cam đoan?", "Characteristic", "Consider", "Promise", "Cover", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bảo vệ, che chở?", "Currently", "Consume", "Accommodate", "Protect", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Danh tiếng?", "Characteristic", "Consider", "Expiration", "Reputation", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Yêu cầu, đòi hỏi?", "Consequence", "Characteristic", "Require", "Persuasion", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Đa dạng, nhiều?", "Characteristic", "Variety", "Expiration", "Cover", 2, ""))
        }
    }
    fun addDataBusiness() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Giải quyết (vấn đề), địa chỉ?", "Address", "Assurance", "Accommodate", "Demonstrate", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bày tỏ, biểu lộ, cho thấy?", "Address", "access", "Demonstrate", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tránh?", "Address", "Avoid", "Demonstrate", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Phát triển?", "Arrangement", "Assurance", "Association", "Develop", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Đánh giá?", "Evaluate", "Avoid", "Demonstrate", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tập hợp, thu thập?", "Address", "Gather", "Demonstrate", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Đề xuất, đề nghị?", "Offer", "Specific", "Accommodate", "Substitution", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Chính?", "Address", "Avoid", "Primarily", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự rủi ro?", "Currently", "Consume", "Accommodate", "Risk", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Chiến lược?", "Address", "Avoid", "Demonstrate", "Strategy", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Khỏe mạnh, tốt, bền bỉ, …?", "Consequence", "Characteristic", "Strong", "Persuasion", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự thay thế?", "Address", "Substitution", "Demonstrate", "Abide by", 2, ""))
        }
    }
    fun addDataConferences() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Phù hợp?", "Accommodate", "Assurance", "Accommodate", "Address", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự sắp xếp, chuẩn bị?", "Accommodate", "Association", "Arrangement", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hiệp hội, đoàn thể?", "Abide by", "Association", "Accommodate", "access", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tham dự, có mặt?", "Accommodate", "Association", "Arrangement", "Attend", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Giữ quan hệ?", "Get in touch", "Agreement", "Cancellation", "Address", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tổ chức?", "Accommodate", "Hold", "Arrangement", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Địa điểm, vị trí?", "Location", "Specific", "Accommodate", "Substitution", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Chật ních, đông nghịt?", "Accommodate", "Association", "Overcrowded", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Đăng ký?", "Currently", "Consume", "Accommodate", "Register", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Chọn lựa?", "Accommodate", "Association", "Arrangement", "Select", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Buổi (học)/phiên, kỳ (họp)?", "Accommodate", "Association", "Session", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tham gia, tham dự?", "Consequence", "Take part in", "Accommodate", "Persuasion", 2, ""))
        }
    }

    fun addDataComputers() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cấp cho, phân phát, chia phần?", "Access", "Association", "Allocate", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hiển thị, trình bày, trưng bày?", "Display", "Association", "Allocate", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hỏng, thất bại?", "Failure", "Association", "Arrangement", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Nhận ra, đoán ra, tìm hiểu?", "Access", "Association", "Figure out", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bỏ qua, phớt lờ?", "Accommodate", "Association", "Arrangement", "Ignore", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tìm kiếm, tìm hiểu?", "Consequence", "Characteristic", "Accommodate", "Search", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Đóng lại, ngừng lại; tắt máy?", "Access", "Association", "Shutdown", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, ""))
        }
    }

    fun addDataOfficeTech() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Có đủ khả năng?", "Access", "Association", "Affordable", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Khi cần, lúc cần thiết?", "Access", "As needed", "Affordable", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Chịu trách nhiệm về?", "As needed", "Association", "Arrangement", "Be in charge of", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Khả năng, năng lực/ sức chứa?", "Capacity", "Association", "Allocate", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bền, lâu bền?", "Access", "Durable", "Allocate", "Abide by", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Khởi xướng?", "Initiative", "Association", "Durable", "Allocate", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Thuộc về vật lý, theo quy luật tự nhiên?", "Access", "Association", "Physically", "Allocate", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Nhà cung cấp?", "Accommodate", "Association", "Arrangement", "Provider", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Lặp lại, tái lặp?", "Reduction", "Physically", "Accommodate", "Recur", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Nắm bắt tình hình?", "Stock", "Recur", "Stay on top of", "Provider", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Kho/hàng dự trữ?", "Stay on top of", "Stock", "Recur", "Provider", 2, ""))
        }
    }

    fun addDataOfficeProducts() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Sự đánh giá, sự nhận thức?", "Appreciation", "Association", "Arrangement", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bao gồm?", "Appreciation", "Association", "Be made of", "Abide by", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Mang đến, mang lại?", "Appreciation", "Bring in", "Be made of", "Display", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bình thường, tự nhiên, không trịnh trọng?", "Appreciation", "Bring in", "Be made of", "Casually", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Quy định, quy tắc, luật lệ, đạo lý?", "Code", "Be made of", "Appreciation", "Bring in", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Thể hiện, phơi bày?", "Appreciation", "Expose", "Allocate", "Bring in", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Nhìn lướt qua, thoáng qua?", "Glimpse", "Association", "Appreciation", "Bring in", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hết, không còn?", "Be made of", "Appreciation", "Out of", "Bring in", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Hết hạn, lỗi thời, lạc hậu?", "Bring in", "Association", "Appreciation", "Outdated", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Thủ tục, thói quen, bài tập?", "Bring in", "Characteristic", "Accommodate", "Practice", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tăng cường, củng cố?", "Access", "Bring in", "Reinforce", "Be made of", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Bằng lời nói?", "Bring in", "Verbally", "Arrangement", "Be made of", 2, ""))
        }
    }

    fun addDataElectronics() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("điện tử?", "Electronics", "Association", "Arrangement", "Abide by", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("đĩa (vi tính, nhạc, …)?", "Electronics", "Association", "Arrangement", "disk", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("làm cho cho thuận tiện?", "Electronics", "Association", "facilitate", "disk", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("mạng lưới?", "Electronics", "network", "facilitate", "disk", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("tính phổ biến, đại chúng?", "popularity", "revolution", "facilitate", "disk", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("xử lý?", "Electronics", "revolution", "facilitate", "process", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("thay thế?", "Electronics", "network", "replace", "store", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("cuộc cách mạng, đột phá?", "Electronics", "revolution", "facilitate", "store", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("nhạy bén, thông minh/ đột ngột?", "sharp", "network", "facilitate", "disk", 1, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("kỹ năng?", "Electronics", "network", "facilitate", "skill", 4, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("phần mềm, chương trình máy tính?", "Electronics", "network", "software", "store", 3, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("lưu trữ?", "Electronics", "store", "facilitate", "disk", 2, ""))
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("thuộc về kỹ thuật, về kỹ thuật?", "network", "technical", "facilitate", "replace", 2, ""))
        }
    }
    fun addDataCorrespondence() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
    fun addDataJob() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
    fun addDataApply() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
    fun addDataHiring() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
    fun addDataSalary() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
    fun addDataPromotions() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
    fun addDataShopping() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
    fun addDataOder() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Truy cập?", "Access", "Association", "Arrangement", "Abide by", 1, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Cấp cho, phân phát, chia phần?",
                    "Access",
                    "Association",
                    "Allocate",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Tương thích?", "Access", "Compatible", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Xóa?", "Accommodate", "Association", "Arrangement", "Delete", 4, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hiển thị, trình bày, trưng bày?",
                    "Display",
                    "Association",
                    "Allocate",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Gấp đôi, nhân đôi?", "Access", "Duplicate", "Allocate", "Abide by", 2, "")
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Hỏng, thất bại?",
                    "Failure",
                    "Association",
                    "Arrangement",
                    "Abide by",
                    1,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Nhận ra, đoán ra, tìm hiểu?",
                    "Access",
                    "Association",
                    "Figure out",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Bỏ qua, phớt lờ?",
                    "Accommodate",
                    "Association",
                    "Arrangement",
                    "Ignore",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Tìm kiếm, tìm hiểu?",
                    "Consequence",
                    "Characteristic",
                    "Accommodate",
                    "Search",
                    4,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question(
                    "Đóng lại, ngừng lại; tắt máy?",
                    "Access",
                    "Association",
                    "Shutdown",
                    "Abide by",
                    3,
                    ""
                )
            )
            VocabularyDatabase.getInstance(context).questionDao().insertQuestion(
                Question("Cảnh báo?", "Accommodate", "Warning", "Arrangement", "Abide by", 2, "")
            )
        }
    }
}