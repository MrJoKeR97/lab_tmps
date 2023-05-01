package com.example.taskmanager

// 1. Single Responsibility Principle (SRP)
interface Task {
    val id: Int
    val name: String
    val isCompleted: Boolean
    fun completeTask()
}



