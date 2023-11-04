# LogParserThreadPool vs LogParserParallel

## Description

A program that reads a log file, with each line representing a log entry, and interprets it by replacing the IP address with the domain name.

## Implementation

The program was initially developed as a Single Thread application and later, utilizing a Thread Pool, highlighting significant differences in execution times.
For address translation, the InetAddress library was used.
