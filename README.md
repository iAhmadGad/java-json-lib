<h1 align="center">Java JSON Handler
<br/>
  
`iahmadgad.json` Package
</h1>

A simple Java package that Handles JSON. ☕
## 💁‍♂️ About:
- This project is just a simple project I made as a beginner, it is actually my second project after [**JCalculator**](https://github.com/iAhmadGad/JCalculator).
## 🤔 How to use
- Download the latest release from [**here**](https://github.com/iAhmadGad/Java-JSON-Handler/releases).
- Add package to your build using command-line, or your IDE like Eclipse or Netbeans.
- Here you are! Start making JSONObjects & JSONArrays, read them, write them & even extract them to Java Arrays & Objects.

Here is an example on how to make a new JSONObject:
```
JSONObject obj = new JSONObject();
obj.put("$id", "User-info");
obj.put("name", "Gelobt");
obj.put("age", 16);
obj.put("isAlive", true);
```
& here is an example on how to make a new JSONArray:
```
JSONArray arr = new JSONArray();
arr.add("Java");
arr.add("C++");
arr.add("Cats");
```
then you can put this array in the JSONObject you`ve just created:
```
obj.put("THINGS I LOVE", arr);
```
last but Not least, write this JSONObject in a JSON file:
```
obj.write(new File("User-info.json"));
```
& here is your JSON file:
```
{
     "isAlive":true
     ,"name":"Gelobt"
     ,"THINGS I LOVE":     [
          "Java"
          ,"C++"
          ,"Cats"
     ]

     ,"age":16
     ,"$id":"User-info"
}
```
## 📄 Repo Index:
- [**iahmadgad.json**](https://github.com/iAhmadGad/Java-JSON-Handler/tree/main/src/iahmadgad/json) package
- [**Documentations**](https://github.com/iAhmadGad/Java-JSON-Handler/tree/main/docs)
## ⚖️ License:
- [**GPL-3.0**](https://github.com/iAhmadGad/Java-JSON-Handler/blob/main/LICENSE)
## 👀 See:
- [**JavaDoc**](https://iahmadgad.github.io/Java-JSON-Handler/iahmadgad/json/package-summary.html)
