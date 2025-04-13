/* filepath: /Users/aaronjardenil/Documents/GitHub/distributed-fault-tolerance/frontend/scripts.js */
const BASE_URL = "http://localhost";
const ENDPOINTS = {
  VIEW_GRADES: `${BASE_URL}:8092/grades`,
  ENROLL: `${BASE_URL}:8091/enrollments`,
  UPLOAD_GRADE: `${BASE_URL}:8093/grades`,
  VIEW_COURSES: `${BASE_URL}:8090/courses`,
  VIEW_ENROLLMENTS: `${BASE_URL}:8091/enrollments`
};

let jwtToken = "";

function showResponse(data) {
  document.getElementById("response").textContent = JSON.stringify(data, null, 2);
}

function updateLoginStatus(isLoggedIn) {
  const statusElement = document.getElementById("loginStatus");
  statusElement.textContent = isLoggedIn ? "Status: Logged In" : "Status: Not Logged In";
}

function login() {
  const springBootLoginUrl = "http://localhost:8080/login";
  const redirectUri = encodeURIComponent(window.location.origin + "/");
  window.location.href = `${springBootLoginUrl}?redirect_uri=${redirectUri}`;
}

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

window.onload = function () {
  extractJwtTokenFromUrl();
};

async function viewGrades() {
  if (!jwtToken) {
    alert("You must be logged in to view grades.");
    return;
  }

  try {
    const res = await fetch(ENDPOINTS.VIEW_GRADES, {
      headers: { "Authorization": "Bearer " + jwtToken }
    });

    if (res.status === 401 || res.status === 403) {
      alert("Session expired. Please log in again.");
      return;
    }

    if (!res.ok) {
      const errorData = await res.json();
      alert(`Error ${res.status}: ${JSON.stringify(errorData)}`);
      return;
    }

    const data = await res.json();
    showResponse(data);
  } catch (error) {
    alert(`The 'View Grades' feature is currently down. Error: ${error.message}`);
  }
}

async function enroll() {
  const courseCode = document.getElementById("enrollCourseCode").value;

  if (!jwtToken) {
    alert("You must be logged in to enroll.");
    return;
  }

  if (!courseCode) {
    alert("Please enter a course code.");
    return;
  }

  try {
    const res = await fetch(ENDPOINTS.ENROLL, {
      method: "POST",
      headers: {
        "Authorization": "Bearer " + jwtToken,
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ code: courseCode })
    });

    if (res.status === 401 || res.status === 403) {
      alert("Session expired. Please log in again.");
      return;
    }

    const data = await res.json();
    showResponse(data);
  } catch (error) {
    alert("The 'Enroll' feature is currently down. Please try again later.");
  }
}

async function uploadGrade() {
  const courseCode = document.getElementById("courseCode").value;
  const username = document.getElementById("uploadUsername").value;
  const grade = document.getElementById("grade").value;

  if (!jwtToken) {
    alert("You must be logged in to upload grades.");
    return;
  }

  if (!courseCode || !username || !grade) {
    alert("Please fill in all fields (Course Code, Username, and Grade).");
    return;
  }

  try {
    const res = await fetch(ENDPOINTS.UPLOAD_GRADE, {
      method: "POST",
      headers: {
        "Authorization": "Bearer " + jwtToken,
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        id: {
          username: username,
          code: courseCode
        },
        grade: parseFloat(grade)
      })
    });

    if (res.status === 401) {
      alert("Session expired. Please log in again.");
      return;
    } else if (res.status === 403) {
      alert("You do not have permission to upload grades.");
      return;
    }

    if (!res.ok) {
      const errorData = await res.json();
      alert(`Error ${res.status}: ${JSON.stringify(errorData)}`);
      return;
    }

    const data = await res.json();
    showResponse(data);
  } catch (error) {
    alert("The 'Upload Grade' feature is currently down. Please try again later.");
  }
}

async function viewCourses() {
  if (!jwtToken) {
    alert("You must be logged in to view courses.");
    return;
  }

  try {
    const res = await fetch(ENDPOINTS.VIEW_COURSES, {
      headers: { "Authorization": "Bearer " + jwtToken }
    });

    if (res.status === 401 || res.status === 403) {
      alert("Session expired. Please log in again.");
      return;
    }

    if (!res.ok) {
      const errorData = await res.json();
      alert(`Error ${res.status}: ${JSON.stringify(errorData)}`);
      return;
    }

    const data = await res.json();
    showResponse(data);
  } catch (error) {
    alert("The 'View Courses' feature is currently down. Please try again later.");
  }
}

async function viewEnrollments() {
  if (!jwtToken) {
    alert("You must be logged in to view enrollments.");
    return;
  }

  try {
    const res = await fetch(ENDPOINTS.VIEW_ENROLLMENTS, {
      method: "GET",
      headers: {
        "Authorization": "Bearer " + jwtToken
      }
    });

    if (res.status === 401 || res.status === 403) {
      alert("Session expired. Please log in again.");
      return;
    }

    if (!res.ok) {
      const errorData = await res.json();
      alert(`Error ${res.status}: ${JSON.stringify(errorData)}`);
      return;
    }

    const data = await res.json();
    showResponse(data);
  } catch (error) {
    alert("The 'View Enrollments' feature is currently down. Please try again later.");
  }
}