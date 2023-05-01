package factory

import com.example.taskmanager.Task
import model.SimpleTask

class SimpleTaskFactory : TaskFactory {
    override fun createTask(id: Int, name: String): Task {
        return SimpleTask(id, name)
    }
}