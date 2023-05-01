package builder

import com.example.taskmanager.Task
import model.SimpleTask

class SimpleTaskBuilder : TaskBuilder {
    private var id: Int = 0
    private lateinit var name: String

    override fun setId(id: Int): TaskBuilder {
        this.id = id
        return this
    }

    override fun setName(name: String): TaskBuilder {
        this.name = name
        return this
    }

    override fun build(): Task {
        return SimpleTask(id, name)
    }
}
