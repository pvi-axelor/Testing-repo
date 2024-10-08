package prerit.vishal.models

object MessageStore {

    // list to have the amil messages, the elements may have replies
    private val mainMessageList = mutableListOf<Message>()

    // list to have all the messages, main or replies in single list
    private val allMessageList = mutableListOf<Message>()

    fun getAllMessageList(): MutableList<Message> = allMessageList
    fun getMainMessageList(): MutableList<Message> = mainMessageList

    fun addMessage(message: Message): Message {
        if (!mainMessageList.contains(message)) {
            mainMessageList.add(message)
            allMessageList.add(message)
        }
        return mainMessageList.last()
    }

    fun addReply (id: Int, message: Message): Message {

        var msg: Message? = null
        for (i: Message in allMessageList) {
            if (i.id == id) {
                msg = i
            }
        }

        msg?.replies?.add(message)
        allMessageList.add(message)

        return msg ?: message
    }

    fun deleteMessage(id: Int) {
       val msgList = mainMessageList.filter { it.id == id }
        msgList.forEach {
            if (it.replies.isNotEmpty()) {
                deleteMessage(it.id)
            }
            mainMessageList.remove(it)
            allMessageList.remove(it)
        }
    }
}