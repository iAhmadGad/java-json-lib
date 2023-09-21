# JSONObject Class
 JSONObject is an object contains (key - value) pairs, 
 keys are always Strings, 
 while values can only be integers, doubles, booleans, or nested JSONObjects & JSONArrays, but it can't be chars or any other type.

 ## Constructors:
 - **public JSONObject()**: The main Constructor which initialises a new JSONObject.
 - **public JSONObject(String text)**: The Constructor which creates a new JSONObject from a text.
 - **public JSONObject(File file)**: The Constructor which creates a new JSONObject from a file.
 - **public &lt;T&gt; JSONObject(T object)**: The Constructor which creates a new JSONObject from a Java object.
	 <p>
	 it creates pairs from the object's fields as variables, arrays & lists (with valid values only as mentioned above).