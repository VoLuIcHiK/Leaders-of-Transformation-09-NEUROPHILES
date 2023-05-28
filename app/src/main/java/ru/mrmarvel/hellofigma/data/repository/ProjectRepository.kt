package ru.mrmarvel.hellofigma.data.repository

import kotlinx.coroutines.Dispatchers
import ru.mrmarvel.hellofigma.data.models.Project
import ru.mrmarvel.hellofigma.data.sources.ProjectSource

class ProjectRepository(private val projectSource: ProjectSource) {
    suspend fun getAll(): List<Project> {
        with(Dispatchers.IO) {
            return projectSource.getAll()
        }
    }

}

