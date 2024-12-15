document.getElementById("mySVG1").addEventListener('click', function(event) {
        let svg = event.currentTarget;
        let rect = svg.getBoundingClientRect();
        let svgX = event.clientX - rect.left;
        let svgY = event.clientY - rect.top;
        let rValue = document.querySelector('[id$=":rSelect"]').value;
        let [x, y] = transformSvgToPlane(svgX, svgY, rValue);
        let roundedX = Number(x.toFixed(1));
        PF('sliderWidgetVar').setValue(roundedX);
        document.getElementById('form1:X-value').value = roundedX;
        console.log(x, y, rValue);
        document.getElementById('form1:yInput').value = y;
        document.getElementById('form1:submitButton').click();
});
function doPoint(xValue, yValue, rValue, result) {
    const svg = document.getElementById('mySVG1');
    const y = parseFloat(yValue);
    const r = rValue;
    const x = xValue;
    console.log(svg);
    if (!svg) {
        console.error("SVG element not found!");
        return;
    }
    const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    const scaledX = x * 70 * 2/r + 200;
    const scaledY = -y * 70 * 2/r + 200;
    circle.setAttribute("cx", scaledX);
    circle.setAttribute("cy", scaledY);
    circle.setAttribute("r", 3);
    if (result) {
        circle.setAttribute("fill", "lime");
    }else {
        circle.setAttribute("fill", "red");
    }

    svg.appendChild(circle);
}
function transformSvgToPlane(svgX, svgY, r) {
    let planeX = (svgX - 200) / (140 / r);
    let planeY = (200 - svgY) / (140 / r);
    return [planeX, planeY];
}
function drawPoints(points){
    console.log(points);
    let lastPoint = points[points.length-1];
    for (let i=0; i<points.length -1 ; i++){
        let point = points[i];
        doPoint(point.x, point.y,lastPoint.r, point.isHit);
    }
    doPoint(lastPoint.x,lastPoint.y,lastPoint.r, lastPoint.isHit);
}