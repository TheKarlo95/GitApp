package hr.thekarlo95.api.github.model

enum class OrderParamApiModel(val param: String) {
    STARS("stars"),
    FORKS("forks"),
    UPDATED("updated");
}