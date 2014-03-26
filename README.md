InternetFileMonitor
===================

Java+Bash Internet File Monitor, for linux/unix/mac OS

Context:

We need to constantly monitor some important public files/pages to make sure their content doesn't change (unless we meant it to).

Mission:

The program reads a list of URLs (http or https) from a configuration file. Some URLs are hosted by multiple servers, and we want to check all of them. So the configuration file has pairs of URL+IP. IP can be * (which means perform normal DNS query to obtain the IP). 

An example for the configuration file:

http://somehost.trusteer.com/file1.txt   10.0.0.1
http://somehost.trusteer.com/file1.txt   10.0.0.2
http://somehost.trusteer.com/file1.txt   10.0.0.3
http://somehost.trusteer.com/file2.txt   10.0.0.1
https://somehost.trusteer.com/file2.txt   10.0.0.1
http://somehost.trusteer.com/file2.txt   10.0.0.2
https://somehost.trusteer.com/file2.txt   10.0.0.2
http://somehost.trusteer.com/file2.txt   10.0.0.3
https://somehost.trusteer.com/file2.txt   10.0.0.3
http://another.trusteer.com/file3.txt   *
https://another.trusteer.com/file3.txt   *
http://another.trusteer.com/file4.txt   *
https://another.trusteer.com/file4.txt   *


For each URL, it downloads the page and calculates a SHA1 hash of the body (the entire page/file). Whenever the hash value changes, send an email alert once. The email should include the affected URL and the IP that was modified.

Implementation requirements:

- Use any language, or combination of languages. You may use any unix tools.
- The program should write a log of its operations to a log file.
- The check should run every 10 minutes.
