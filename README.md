# settlers-farmville

The following is a project for our senior capstone course. 

In order to start using this, you must have eclipse and Java jdk install

Next, create a branch for you to develop on. My recommendation would be to name your branch DEV-XX where XX are your first and last initials.

Run the following commands to get the code on your computer:

`git clone https://github.com/thecodesterofficial/settlers-farmville.git

git checkout DEV-XX`

Once the project is on your computer, pull the project into eclipse via file -> open projects from file system. 

You will have to open three projects.

One project is for the server. One project is for the GUI. The other project are the modularized files that are shared by the GUI and server. 

Next, you will have to run it. You may need to go into project properties to make sure the JRE is on the top of the build path. Also, sometimes eclipse is weird on the first build so you may need to go into each project and find the "main" class.

The main class is the class in the project that contains the main function. In order to launch it, right-click and click run as -> java application.

You will want to launch the server first and then the GUI.

After you have made changes to the project, you can commit them by running the following commands.

`git add .

git status 

git commit -m "My message here"

git push`

(Running git status will allow you to see if something looks weird.)

When your code is ready to by push into the main project simply submit a pull request. 

## Comamnds from server to client

### Game Commanawefawfasdfasdfasdfasdfasdfds
game init h
game init robber [location]
game new player [ussername]
game start

### Connect Commands
connect good
connect bad [error]

## Commands from client to server
### Game Commands
game start
### Connect commands
connect [username]

