function runSimulation() {
    fetch("http://localhost:8000/run")
    .then(res => res.text())
    .then(data => {
        document.getElementById("output").innerText = data;
    });
}

function downRouter() {
    alert("Simulate R2 DOWN (handled in backend)");
}

function upRouter() {
    alert("Simulate R2 UP (handled in backend)");
}