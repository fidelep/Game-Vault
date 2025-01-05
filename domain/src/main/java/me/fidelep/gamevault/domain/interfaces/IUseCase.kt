package me.fidelep.gamevault.domain.interfaces

interface IUseCase<O> {
    suspend operator fun invoke(): O
}

interface IParamUseCase<I, O> {
    suspend operator fun invoke(param: I): O
}
