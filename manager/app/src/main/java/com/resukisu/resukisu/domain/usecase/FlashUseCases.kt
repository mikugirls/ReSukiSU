package com.resukisu.resukisu.domain.usecase

import com.resukisu.resukisu.data.flash.FlashRepository

class ObserveKernelFlashUseCase(private val repository: FlashRepository) {
    operator fun invoke() = repository.kernelFlashSession
}

// 修改：增加 kpmPatchEnabled 和 kpmUndoPatch 参数
class StartKernelFlashUseCase(private val repository: FlashRepository) {
    operator fun invoke(
        kernelUri: String,
        selectedSlot: String?,
        kpmPatchEnabled: Boolean,
        kpmUndoPatch: Boolean
    ) = repository.startKernelFlash(kernelUri, selectedSlot, kpmPatchEnabled, kpmUndoPatch)
}
