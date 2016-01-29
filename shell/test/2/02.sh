#!/bin/bash
LogFile="file.log"
date >> $LogFile
finger >> $LogFile
uptime >> $LogFile
exit
