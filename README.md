# QRCode-Splitter
Program to split QR codes between to separate files to allow laser cutting  


Program generates a QR code as a SVG file then runs through the image and ensures every pixel in the QR code is connected. Any pixels that need to be removed to create a path are moved to a secondary image.
This produces a usable stencil and can be used to make a semi-permanent link on any smooth surface and is more durable than paper

Use case: COVID 19 check-in links

Improvements needed: keep square corner makers on both half images to aid in alignment
