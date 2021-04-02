# 🚀 text-to-audio-tool
Simple Java based console tool that transforms your text files to audio files.

### 💻 System Requirements
* ☕️ Java 11 runtime environment installed
* 🏗 Maven (http://maven.apache.org) must be installed
* 👨‍💻 macOS device (the tool uses internal operating system's feature that transforms textual data to audio data)

### 👷‍♂️ How to build

`
⚠️ There are no dedicataed releases available now. So, the only way to use the tool is to build it using guide below.
If you are not scared - then let's go! 🤘
`
1) Open Terminal application and navigate to project's folder
2) Use following command to build jar file `mvn clean package`. That command will build the tool for you and output where to find jar file.
3) Navigate to folder with the jar file

### 🕹 How to use
The usage of the tool is pretty simple. Just use `-help` flag when you execute your jar file and check what other options you have. 

#### 🎓 Examples
Use following command to see available options:
```
java -jar text-to-audio-tool-0.1-SNAPSHOT.jar -help
```

Use following command to transform text file to an audio file:
```
java -jar text-to-audio-tool-0.1-SNAPSHOT.jar -f /Users/anverbogatov/Desktop/temp/text.txt
```
where
* `temp.txt` - is file with written text for which we want to have an audio

As the result `result.aiff` audio file will be generated and saved near your textual file.