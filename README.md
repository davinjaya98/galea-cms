# galea-cms
Headless CMS Galea

Welcome to Galea

This is our first attempt on creating CMS which mainly focus on helping developers!
Most of the functionality of the CMS is to setup the properties of a website in a minimal and efficient way!

# Sample case scenario
Creating a slider that have picture and name on homepage

1. Create new page under page setting (Homepage)
2. Create a custom group under the page setting (Slider Section)
3. Create a custom data under the custom group (Slider Config)
4. Create custom data setting for Slider Config. Different type represent different data type:
- Type 1 => Textfield
- Type 2 => Boolean
- Type 3 => URL Path
- Type 4 => Textarea
- Type 5 => Image Blob
- Type 6 => Rich Text
5. The slider is now configurable by setting the value on custom data value

# Features
The project is built with
1. Spring boot as the core
2. Swagger as the API docs (Please comment the profile section under application properties to disable / enable Swagger for production launch)
3. MySQL as database

# Setup guide
1. Install java
2. Install maven
3. Use command mvn clean install to compile the project on the root folder
4. The jar file will be created under /target/.jar
5. run java -jar target/galea-cms-0.0.1-SNAPSHOT.jar from the root folder