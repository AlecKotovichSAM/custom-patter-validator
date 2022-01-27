Based on https://www.javainuse.com/spring/boot-jwt

## Get Token:

/authenticate

```
{
	"username": "javainuse",
	"password": "password"
}
```

### Header:

Authorization: Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImphdmFpbnVzZSIsInBhc3N3b3JkIjoicGFzc3dvcmQifQ.6SOl9q-NthnhOeR2aaqPUGn-pa7HzV0h1whKjoXFqAO3ayf23eTq8HBkfuCh2a481GZi6xrCC2N4ZUoO5PDuWg


## Register:

/register?lang=ru

```
{
	"username": "Alex",
	"password": "Passw0rd",
	"email" : "e@mail.com"
}
```
