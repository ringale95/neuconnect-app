export const getTag = (tag) => {

    const span = document.createElement("span");
    span.classList.add("badge", "text-bg-secondary", "m-2");

    span.textContent = tag;

    return span;
}