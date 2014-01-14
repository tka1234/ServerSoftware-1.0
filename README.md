AMDGS-Software
==============

Software to control my new Gaming Server:

CPU: AMD A6-5400K 3.6GHz Dual-Core Processor

Motherboard: MSI FM2-A75IA-E53 Mini ITX

RAM: Corsair Vengeance 4GB DDR3-1600

HDD: Seagate Barracuda 500GB 7200RPM

Case: Rosewill V3Plus-B Mini ITX Tower Case, 4x NMB 40mm fans + 3.5" Fan Controller

OS: Ubuntu Linux LTS 12.04

===

This program will eventually be able to load a specified server, and then monitor it, send e-mail or text messages of its status, run in eco-mode where the computer shuts down the server and hibernates at set times, be able to come out of eco-mode to perform other maintenance tasks, be able to switch servers on demand, and be remotely controllable.

This project will be coded using the Oracle Java JDK 1.7 on Ubuntu and Ubuntu Bash scripts.

===

Progress Report:
- Loads Servers: Yes - JARs only.
- Monitors Servers: No - need to correctly implement CPU usage %, network usage %, and HDD usage %.
- Sends Email/Text Notifications: Yes - more conditions for sending will be added.
- Eco mode fully working: Kinda - times glitch out when they are very close and show negative.
- Commands for Sleep (Suspend?): No - need to learn the Linux commands.
- Maintenance Tasks: No - implement the ability to sequentially run all bash files in a folder.
- Remote Controllable: No - need to set up a reccuring server?

===

Extended To-Do List:
- Add capability to launch a bash file for running a server instead of just a jar.
- Make the program launch No-IP DUC at the beginning, and close it when you shut down.
- Implement CPU usage, network usage, RAM usage, HDD usage, etc. and allow them to be monitored.
- Fix bugs with the time system - possible rewrite of how times are handled.
- Figure out how to change the active window and send virtual keystrokes to it
- Run bash files as maintenance
