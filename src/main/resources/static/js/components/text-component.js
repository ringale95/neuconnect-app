// Function to create a text node
export const createTextNode = (text) => {
    return document.createTextNode(text);
}

export const displayOneText = (text) => {
    const textEle = document.createElement("span")
    textEle.classList.add("display-1");
    textEle.innerText = text;
    return textEle;
}

