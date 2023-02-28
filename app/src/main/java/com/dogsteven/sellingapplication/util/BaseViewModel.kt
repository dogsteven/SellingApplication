package com.dogsteven.sellingapplication.util

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogsteven.sellingapplication.common.ApplicationEvent
import com.dogsteven.sellingapplication.domain.model.remote.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    protected val application: Application,
    protected val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val jobs: HashMap<String, Job> = HashMap()

    private val _event: MutableSharedFlow<ApplicationEvent> = MutableSharedFlow()
    val event: SharedFlow<ApplicationEvent> = _event

    protected fun executeJob(key: String, job: suspend () -> Unit) {
        jobs[key]?.cancel()
        jobs[key] = viewModelScope.launch { job() }
    }

    protected suspend fun emitEvent(event: ApplicationEvent) {
        _event.emit(event)
    }
}