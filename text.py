import requests
data={
    "grant_type":"client_credentials",
    "client_id":"courier",
    "client_secret":"eWL%FhU6pj"
}
response = requests.post("http://auth.uat.svz.maxus.lan:443/access_token", data)
print(response)