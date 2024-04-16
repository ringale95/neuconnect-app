import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";
import { reloadTable } from "../common/table.js";

if (window.location.pathname.includes('/manage-users')) {
  document.addEventListener('DOMContentLoaded', () => {
    const searchField = document.getElementById('search-field');
    const dropdownItems = document.querySelectorAll('.dropdown-menu .dropdown-item')
    const previous = document.getElementById('previous');
    const next = document.getElementById('next');

    if (searchField) {
      searchField.addEventListener('input', (event) => loadResults(event, null, null));
    }

     if (previous) {
          previous.addEventListener('click', (event) => {
            const currentPage = document.getElementById("page-number").getAttribute("value");
            if(currentPage == 1){
                showToast("No Previous!", "fail");
                return;
            }
            loadResults(event, null, "previous");
          });
    }

     if (next) {
         next.addEventListener('click', (event) => {
              const currentPage = document.getElementById("page-number").getAttribute('value');
              const totalPage = document.getElementById("total-count").getAttribute('value');
              console.log("Page Information....");
              console.log("Page Current:", currentPage);
              console.log("Total Page", totalPage);

              if(currentPage === totalPage){
                  showToast("No next!", "fail");
                  return;
              }
             loadResults(event, null, "next");}
             );
         }

    dropdownItems.forEach(item => {
        item.addEventListener('click', function(event) {
            const selectedRole = event.target.textContent.trim();
            loadResults(event, selectedRole);
        });
    });
  });
}

const loadResults = (event, role, action) => {
    const searchText = document.getElementById('search-field').value;
    const isAdmin = role === 'Admin'? true : false;
    const isAuthority = role === 'Authority'? true : false;
    const isStudent = role === 'Student'? true : false;
    let value = 1;

    if(action != null){
        const currentPage = parseInt(document.getElementById("page-number").getAttribute('value'));
        const adjustor = action === 'next'? 1: -1;
        value = currentPage + adjustor;
    }

    const options = {
        pageNumber: value,
        pageSize: 12,
        filterOption: {
            searchKey: searchText,
            admin: isAdmin,
            authority: isAuthority,
            student: isStudent
        }
    };

  makeRequest('/api/users/fetch', 'POST', options, onSuccess, onError);
};

const updatePageInfo = (data) => {
    // Extract pageNumber and totalPages from the data object
    const { pageNumber, totalPages } = data;

    // Get the elements with IDs "page-number" and "total-count"
    const pageNumberElement = document.getElementById('page-number');
    const totalCountElement = document.getElementById('total-count');

    // Set the text content and value attributes of the "page-number" span
    pageNumberElement.textContent = pageNumber;
    pageNumberElement.setAttribute('value', pageNumber);

    // Set the text content and value attributes of the "total-count" span
    totalCountElement.textContent = totalPages;
    totalCountElement.setAttribute('value', totalPages);
}


const onSuccess = (data) => {
    reloadTable(data);
    updatePageInfo(data);
};

const onError = (error) => {
  showToast("Request Failed", "fail");
};


export { loadResults };
