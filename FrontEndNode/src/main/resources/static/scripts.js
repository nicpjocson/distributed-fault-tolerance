/* filepath: /Users/aaronjardenil/Documents/GitHub/distributed-fault-tolerance/frontend/scripts.js */
let jwtToken = "eyJraWQiOiI0ZmUzMzdiNS0zMTU2LTQ2NWEtOWM0ZC1mOTA3Y2I4MjYwODAiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhYXJvbl9qYXJkZW5pbEBkbHN1LmVkdS5waCIsImF1ZCI6InByb2R1Y3QtY2xpZW50IiwibmJmIjoxNzQ0NTUzODM1LCJyb2xlIjoiU1RVREVOVCIsInNjb3BlIjpbInByb2R1Y3QucmVhZCIsInByb2R1Y3Qud3JpdGUiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNzQ0NTU0NzM1LCJpYXQiOjE3NDQ1NTM4MzV9.CT0kiasaLXK2sRy5RSMKwotBR-bHAqgYW-jp4YytI1IqyOhWCYfpxa_45bAzGUy-SMarg9TF0v_7WRCjI1Rre23QydAcbUfyvhfQQE8Q8EK--Jhl24KStaC8J7QMusux7tom5ZhDO25Q2znTA-EpeFKnEtQ_TLDrq_7W2spvlJ5iitgkSEG9BCt8m2BP6gcti5NlIL2wjVuI1yhUeWov2VHYfXmHxSjVuVr1cPps4z8A8OxXKbPleX7LjBphEZjLvDcqfiPAVCU1ClITX1R9lbNdDsz0fdxXoOE6zJfhfiSxvfu0MLqk6567GgoX8_1w1P91ibnAPpu-h7TMeLLFow";

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


/*
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
*/

function setManualToken() {
  const tokenInput = document.getElementById("manualToken").value;
  if (tokenInput) {
    jwtToken = tokenInput;
    updateLoginStatus(true);
    alert("Token set successfully!");
  } else {
    alert("Please enter a valid token");
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

async function viewCourses() {
  if (!jwtToken) {
    alert("You must be logged in to view courses.");
    return;
  }

  const res = await fetch("http://localhost:8090/courses", {
    headers: { "Authorization": "Bearer " + jwtToken }
  });

  if (!res.ok) {
    const errorData = await res.json();
    alert(`Error ${res.status}: ${JSON.stringify(errorData)}`);
    return;
  }

  const data = await res.json();
  showResponse(data);
}