import { makeRequest } from '../common/api.js';
import { showToast } from "../common/toast.js";

let notifications = [];

const allNotificationOptions = {
  pageNumber : 1,
  pageSize : 12
};

const unreadNotificationOptions = {...allNotificationOptions,
  filterOption: {
    status: "GENERATED"
  }
};

if (window.location.pathname.includes('/pushes')) {
  document.addEventListener('DOMContentLoaded', () => {
    fetchNotifications(allNotificationOptions);

    const dropdown = document.getElementById("filterSelect");
    dropdown.addEventListener('change', (event) => {
        if(dropdown.value === 'UNREAD')
            fetchNotifications(unreadNotificationOptions);
        else
            fetchNotifications(allNotificationOptions);
    });
  });
}

const fetchNotifications = (options) => {
    // Call AJAX:
    makeRequest('/api/notifications/fetch', 'POST', options, onSuccess, onError);
}

const onSuccess = (data) => {
    // Store data in array notifications
    notifications = data.records
    // Display Cards
    displayNotifications();
};

const onError = (error) => {
  showToast("Request Failed", "fail");
};

const displayNotifications = () => {
    const notificationList = document.getElementById("notificationList");
    notificationList.innerHTML = '';

    notifications.forEach(notification => {
        const card = document.createElement("div");
        card.classList.add("card");

        if (notification.notificationStatus === "GENERATED") {
          card.classList.add("text-white","bg-primary", "mb-3");
        } else
            card.classList.add("text-dark","bg-light", "mb-3");

        const cardBody = document.createElement("div");
        cardBody.classList.add("card-body");

        const cardText = document.createElement("p");
        cardText.classList.add("card-text");
        cardText.textContent = notification.message;

        cardBody.appendChild(cardText);
        card.appendChild(cardBody);
        notificationList.appendChild(card);
    });
}


export { fetchNotifications };
