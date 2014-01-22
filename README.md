AMDGS-Software
==============
Software to control my new Gaming Server:
 - CPU: AMD A6-5400K 3.6GHz Dual-Core Processor
 - Motherboard: MSI FM2-A75IA-E53 Mini ITX
 - RAM: Corsair Vengeance 4GB DDR3-1600
 - HDD: Seagate Barracuda 500GB 7200RPM
 - Case: Rosewill V3Plus-B Mini ITX Tower Case, 4x NMB 40mm fans + 3.5" Fan Controller
 - OS: Ubuntu Linux LTS 12.04
===
This program will eventually be able to load a specified server, and then monitor it, send e-mail or text messages of its status, run in eco-mode where the computer shuts down the server and hibernates at set times, be able to come out of eco-mode to perform other maintenance tasks, be able to switch servers on demand, and be remotely controllable via e-mail or text messages. Another idea I have is making an applet for users to check the server's status, or possibly "call" it to turn it on when they want to use it.

This project will be coded using the Oracle Java JDK 1.7 on Ubuntu and Ubuntu Bash scripting.
Required Java APIs:
 - Hyperic Sigar 1.6.4
 - JavaMail 1.4.7
===
Progress Report:
 - Loads Servers: Yes - may have some issues with sudo commands.
 - Closes Servers: No - need to figure out how to interface with an existing command line.
 - Monitors Servers: No - need to implement CPU usage %, network usage %, and HDD usage %.
 - Sends Email/Text Notifications: Yes - capable but not fully implemented.
 - Eco mode fully working: Yes - possible bugs in maintenance mode though. Like a 1/60 chance of not getting triggered.
 - Commands for Hibernate: Yes - correct commands implemented but not tested.
 - Maintenance Tasks: No - implement the ability to sequentially run all required bash files in a folder.
 - Remote Controllable: No - server can recieve e-mails or texts but cannot do anything with them.
===
Extended To-Do List:
 - Either interface with the No-IP DUC launching or buy that $18 router to do it for you.
 - Implement CPU usage, network usage, RAM usage, HDD usage, etc. and send messages.
 - Figure out how to send keystrokes to an active terminal to close the server gracefully.
 - Run several bash files as maintenance.
 - Actually test hibernation modes to prevent failure.
 - Stress-test the server and the program.
