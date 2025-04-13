/* filepath: /Users/aaronjardenil/Documents/GitHub/distributed-fault-tolerance/frontend/scripts.js */
let jwtToken = "";

function showResponse(data) {
  document.getElementById("response").textContent = JSON.stringify(data, null, 2);
}

function updateLoginStatus(isLoggedIn) {
  const statusElement = document.getElementById("loginStatus");
  statusElement.textContent = isLoggedIn ? "Status: Logged In" : "Status: Not Logged In";
}

function login() {
  // Redirect to the Spring Boot authentication server's login page
  const springBootLoginUrl = "http://localhost:8080/login"; // Replace with your Spring Boot login URL
  const redirectUri = encodeURIComponent(window.location.origin + "/"); // Redirect back to your frontend
  window.location.href = `${springBootLoginUrl}?redirect_uri=${redirectUri}`;
}

function extractJwtTokenFromUrl() {
  const urlParams = new URLSearchParams(window.location.search);
  const token = urlParams.get("token"); // Replace "token" with the actual query parameter name used by your Spring Boot server
  if (token) {
    jwtToken = token;
    updateLoginStatus(true);
    alert("Login successful!");
    // Optionally, clear the query parameters from the URL
    window.history.replaceState({}, document.title, window.location.pathname);
  }
}

// Call this function on page load to check for the token
window.onload = function () {
  extractJwtTokenFromUrl();
};

async function viewGrades() {
  if (!jwtToken) {
    alert("You must be logged in to view grades.");
    return;
  }

  const res = await fetch("http://localhost:8001/view-grades", {
    headers: { "Authorization": "Bearer " + jwtToken }
  });
  const data = await res.json();
  showResponse(data);
}

async function enroll() {
  const res = await fetch("http://localhost:8002/enroll", {
    method: "POST",
    headers: { "Authorization": "Bearer " + jwtToken }
  });
  const data = await res.json();
  showResponse(data);
}

async function uploadGrade() {
  const courseCode = document.getElementById("courseCode").value;
  const username = document.getElementById("uploadUsername").value;
  const grade = document.getElementById("grade").value;

  const res = await fetch("http://localhost:8004/upload-grade", {
    method: "POST",
    headers: {
      "Authorization": "Bearer " + jwtToken,
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      courseCode,
      username,
      grade
    })
  });

  const data = await res.json();
  showResponse(data);
}