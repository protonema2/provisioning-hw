API Usage
Provision a Device
When a device boots up, it makes an API call:

http
Copy
GET /api/v1/provisioning/{mac_address}
Response if the device is found:
✅ Desk Device → Property file
ini
Copy
{"status":"Ok","data":{"macAddress":"a1-b2-c3-d4-e5-f6","status":"Provisioned","fragment":"password=white\nport=5161\ndomain=sip.anotherdomain.com\ncodecs=G711,G729,OPUS\ntimeout=10\nusername=walter"}}

✅ Conference Device → JSON file
json
Copy
{"status":"Ok","data":{"macAddress":"1a-2b-3c-4d-5e-6f","status":"Provisioned","fragment":"{\"domain\":\"sip.anotherdomain.com\",\"port\":\"5161\",\"timeout\":10,\"username\":\"eric\",\"password\":\"blue\",\"codecs\":\"G711,G729,OPUS\"}"}}
Response if the device is NOT found:
json
Copy
{"status":"Not Found","errorCode":"404","message":"Mac Device not found"}404

Create a feature branch (git checkout -b feature-name)

Commit your changes (git commit -m "Added new feature")

Push to the branch (git push origin feature-name)

Open a Pull Request

📜 License
This project is licensed under the MIT License.

This README is now ready to go! 🎉 You can add it to your GitHub repository. Let me know if you need any modifications! 🚀
