let r5Disabled = false;
let r6Disabled = false;
let packetCounter = 0;

setInterval(() => {
    document.getElementById("clock").innerText =
        new Date().toLocaleTimeString();
}, 1000);

async function runSimulation() {

    packetCounter += 3;

    document.getElementById("packetCount").innerText =
        "Packets Sent : " + packetCounter;

    if (!r5Disabled) {
        animatePacket("main");
    }
    else if (!r6Disabled) {
        animatePacket("upper");
    }
    else {
        animatePacket("lower");
    }

    const response =
        await fetch("http://localhost:8000/run");

    const data = await response.text();

    document.getElementById("output").innerText = data;

    const output =
        document.getElementById("output");

    output.scrollTop =
        output.scrollHeight;
}

function animatePacket(route) {

    const packet =
        document.getElementById("packet");

    packet.style.display = "block";

    document.querySelectorAll(".link")
        .forEach(link =>
            link.classList.remove("active-link"));

    let positions = [];

    if (route === "main") {

        ["l1","l2","l3","l4","l5","l6","l7","l8","l9b"]
            .forEach(l =>
                document.querySelector("." + l)
                    .classList.add("active-link"));

        positions = [
            {left:"80px",top:"315px"},
            {left:"220px",top:"315px"},
            {left:"360px",top:"315px"},
            {left:"500px",top:"315px"},
            {left:"640px",top:"315px"},
            {left:"780px",top:"315px"},
            {left:"920px",top:"315px"},
            {left:"1060px",top:"315px"},
            {left:"1200px",top:"315px"},
            {left:"1340px",top:"315px"}
        ];
    }

    else if (route === "upper") {

        ["l1","l2","l3","l4","l9","l10","l11","l7","l8","l9b"]
            .forEach(l =>
                document.querySelector("." + l)
                    .classList.add("active-link"));

        positions = [
            {left:"80px",top:"315px"},
            {left:"220px",top:"315px"},
            {left:"360px",top:"315px"},
            {left:"500px",top:"315px"},
            {left:"640px",top:"315px"},
            {left:"640px",top:"120px"},
            {left:"920px",top:"120px"},
            {left:"920px",top:"315px"},
            {left:"1060px",top:"315px"},
            {left:"1200px",top:"315px"},
            {left:"1340px",top:"315px"}
        ];
    }

    else {

        ["l1","l2","l3","l12","l13","l14","l7","l8","l9b"]
            .forEach(l =>
                document.querySelector("." + l)
                    .classList.add("active-link"));

        positions = [
            {left:"80px",top:"315px"},
            {left:"220px",top:"315px"},
            {left:"360px",top:"315px"},
            {left:"640px",top:"315px"},
            {left:"640px",top:"520px"},
            {left:"920px",top:"520px"},
            {left:"920px",top:"315px"},
            {left:"1060px",top:"315px"},
            {left:"1200px",top:"315px"},
            {left:"1340px",top:"315px"}
        ];
    }

    let i = 0;

    function moveNext() {

        if (i >= positions.length)
            return;

        packet.style.left = positions[i].left;
        packet.style.top = positions[i].top;

        i++;

        setTimeout(moveNext, 700);
    }

    moveNext();
}

async function disableRouter() {

    r5Disabled = true;

    const response =
        await fetch("http://localhost:8000/disableR5");

    const data = await response.text();

    document.getElementById("r5").style.background =
        "#ef4444";

    document.getElementById("status-r5").innerText =
        "R5 : DOWN";

    document.getElementById("output").innerText =
        data;
}

async function enableRouter() {

    r5Disabled = false;

    const response =
        await fetch("http://localhost:8000/enableR5");

    const data = await response.text();

    document.getElementById("r5").style.background =
        "#38bdf8";

    document.getElementById("status-r5").innerText =
        "R5 : ACTIVE";

    document.getElementById("output").innerText =
        data;
}

async function disableRouter6() {

    r6Disabled = true;

    const response =
        await fetch("http://localhost:8000/disableR6");

    const data = await response.text();

    document.getElementById("r6").style.background =
        "#ef4444";

    document.getElementById("output").innerText =
        data;
}
