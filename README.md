# GPS Track
**A simple way to store and share GPS track online**

This is a proof-of-concept version using Spring Boot, Spring JPA, Spring Security.

## Functional service
Because this is a prototype version, GPS Track focus on only 1 WS API: GPS API.

<img width="147" height="411" alt="Service" src="https://github.com/hoangnam2261/trackGPS/raw/master/diagram/system/functional_service.png">

### GPS Service
Allow users to upload "gpx" file and store mandatory information such as "metadata, waypoint, track",
view a list of "Latest track" from our users
and allow users to view details of their gpx file.

Method	| Path	| Description	| User authenticated|
------------- | ------------------------- | ------------- |:-------------:|
POST	| /gpx/uploadFile	| Upload "gpx" file	| × |
GET	| /gpx/{gpxId}	| Get gpx file for view details	|  |
GET	| /gpx/latest?pageSize={pageSize}&offset={offset}	| View a list of latest tracks	|   |

#### Notes
- In this project, I use H2 as a primary database for gps service. It is very easy switching to another database by change configuration.

## System diagram
<img width="880" height="751" alt="System" src="https://github.com/hoangnam2261/trackGPS/raw/master/diagram/system/system.png">

## Authentication
In this project, for simplicity, I use basic authentication for API endpoint /gpx/uploadFile.
Only logged in users can upload the gpx file. I create a demo account with the credential:<br></br>
User: hoangnam2261<br></br>
Password: 123456

#### Upload file flow
<img width="683" height="734" alt="Upload file flow" src="https://github.com/hoangnam2261/trackGPS/raw/master/diagram/work_flow/upload_file.png">

#### Get latest tracks
<img width="508" height="564" alt="Get latest tracks" src="https://github.com/hoangnam2261/trackGPS/raw/master/diagram/work_flow/get_latest_track.png">

#### View the details of track file
<img width="443" height="744" alt="Details of track" src="https://github.com/hoangnam2261/trackGPS/raw/master/diagram/work_flow/view_detail.png">
