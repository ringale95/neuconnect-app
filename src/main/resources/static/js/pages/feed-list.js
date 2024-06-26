import { renderPostList } from "../components/post-list.js";
import { displayOneText } from "../components/text-component.js";
import { getPaginatedLists } from "../services/post-service.js";

let baseOption = {
    pageSize: 10,
    pageNumber: 1
}

const injectPostInPostContainer = (data) => {
    const posts = data.records;
    const postContainer = document.getElementById('feed-container');
    // postContainer.innerHTML = '';

    if(posts.length === 0){
        const failed = displayOneText("No Records found!");
        postContainer.appendChild(failed);
        return;
    }
    const renderPosts = renderPostList(posts);
    postContainer.appendChild(renderPosts);
}

const showFailedMessageInContainer = (error) => {
    const postContainer = document.getElementById('feed-container');
    const failed = displayOneText("Request Failed!!!");
    postContainer.appendChild(failed);
}

export const onPageLoad = (event) => {
    getPaginatedLists(baseOption, injectPostInPostContainer, showFailedMessageInContainer);
    const button = document.getElementById("load-more");
    button.addEventListener("click", (event)=>{
        baseOption.pageNumber++;
        getPaginatedLists(baseOption, injectPostInPostContainer, showFailedMessageInContainer);
    })

    const searchBtn = document.getElementById("search-field");
    searchBtn.addEventListener("input", (event)=>{
        const searchText = document.getElementById('search-field').value;
        baseOption.pageNumber = 1;
        let filterOption = {
            searchKey:searchText
        }
        getPaginatedLists({...baseOption, filterOption}, (data) => {document.getElementById('feed-container').innerHTML = '';injectPostInPostContainer(data)}, showFailedMessageInContainer);
    })
}

if (window.location.pathname.includes('/feed')) {
    document.addEventListener('DOMContentLoaded', onPageLoad);
}
