<html>
	<head>
	    <title>Country Lookup</title>
	</head>
<body>
<h2><%= "Hello World!" %></h2>
<h2>Find countries by name</h2>
    <form method="post" action="lookup">
        <input type="text" name="country" placeholder="Enter country name">
        <input type="submit" value="Search">
    </form>

    <hr/>
    <div>
        ${result}
    </div>
</body>
</html>
