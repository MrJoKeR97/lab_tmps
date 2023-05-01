package notification

class SimpleNotifier : Notifiable {
    override fun sendNotification(message: String) {
        println("Notification: $message")
    }
}