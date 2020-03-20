Tile-Matching Game Environment (TMGE)

Instructions on executing the game jar file
Download Runnable Jar: 
https://drive.google.com/open?id=1QUXatuJq304D4rZSoXDBRykuMoJuU_2g


Instructions for running the jar file on WindowsOS 

Please note the jar file was built using java version 11
On windows, open the cmd terminal.
Navigate to project.jar working directory
Type the command:
java -jar --module-path $PATH_TO_FX$ --add-modules javafx.controls,javafx.graphics,javafx.fxml,javafx.media project.jar
This assumes the user has set up the System Environment Variable PATH_TO_FX to point to a javafx sdk version 11 lib with javafx modules.  
The user can also replace $PATH_TO_FX$ with the absolute path to the javafx sdk lib such as: "C:\Program Files\Java\javafx-sdk-11.0.2\lib"
Hit Enter and the Game Hub will be displayed

Instructions for running the jar file on MacOS 

Please note the jar file was built using java version 11
Download http://gluonhq.com/download/javafx-11-0-2-sdk-mac
Unzip “openjfx-11.0.2_osx-x64_bin-sdk.zip” and “javafx-sdk-11.0.2” folder appears.
On Mac, open the terminal.
Navigate to project.jar working directory.
Type the command:
java -jar --module-path /Users/XXXXXX/Downloads/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.graphics,javafx.fxml,javafx.media project.jar
Make sure the path is correct for both project.jar and javafx libraries

