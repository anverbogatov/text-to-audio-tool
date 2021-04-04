# anbo-platform

[![Maven](https://github.com/anverbogatov/text-to-audio-tool/workflows/Java%20CI%20with%20Maven/badge.svg)](https://github.com/anverbogatov/text-to-audio-tool/workflows/Java%20CI%20with%20Maven/badge.svg)

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
* `temp.txt` - is a file with written text for which we want to have an audio

As the result `*.aiff` audio files will be generated and saved near your textual file.

### 🚧 How to write right textual file?
The tool support generation of multiple audio files out from `*.txt` & `*.md` files that have required file structure.

Use `### ` symbols to define new text block in your file. Add some text right after these symbols to name that text block. 
That information will be used later during an audio files generation.

Then, start new line and write down your script that must be pronounced by machine voice.

That is it.

So, your file must follow following structure:
```
### [name of text block]
[text to audio]

### [name of another text block]
[text to audio]
```

And here is example:
```
### Introduction
Hello dear comrades! Let's talk about Soviet Russia today.

### What is Soviet Russia?
Soviet Russia is the greatest country in the World.

### Outro
Let the Great Bear be with you my dear comrade!
```

As the result of the tool work you will have following files in the same folder where your textual file is located:
```
0-introduction.aiff
1-what-is-soviet-russia?.aiff
2-outro.aiff
```

### 📘 Where the tool can be useful?
Basically, it is up to you, my friend! 🤝

But me personally found that tool useful for video edit and production. When I need to do a voice over and I do not want to record myself or somebody else, I can use that tool for that.
Here is example of video I produced with the tool:

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/P8rkvDco8ac/0.jpg)](https://www.youtube.com/watch?v=P8rkvDco8ac)
