<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather Details</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background: linear-gradient(to right, #6dd5ed, #2193b0);
        color: #fff;
        text-align: center;
    }
    h1 {
        margin: 20px 0;
        font-size: 24px;
        color: #f7f7f7;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
    }
    
    form {
        margin-top: 30px;
    }
    input[type="text"] {
        padding: 10px;
        width: 300px;
        border: 2px solid #ddd;
        border-radius: 5px;
        font-size: 16px;
    }
    button {
        padding: 10px 20px;
        margin-left: 10px;
        background-color: #2193b0;
        color: #fff;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background-color 0.3s;
    }
    button:hover {
        background-color: #6dd5ed;
        transform: scale(1.05);
        }
    .container {
        margin: 50px auto;
        max-width: 600px;
        background: rgba(0, 0, 0, 0.6); /* Slightly dark background for text contrast */
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.3);
    }
</style>
</head>
<body>

<div class="container">
	<h1 style="font-size:30px ;">${city}</h1>
    <h1>Date: ${date}</h1>
    <h1>Time: ${time}</h1>
    <h1>Temperature: ${temp} °C</h1>
    <h1>Humidity: ${humi}%</h1>
    <h1>Wind: ${wind} km/h</h1>
    <h1>Weather: ${weather}</h1>

    <form action="MyServlet" method="post">
        <input type="text" name="input" placeholder="Enter city">
        <button name="submit">Submit</button>
    </form>
</div>

</body>
</html>
		