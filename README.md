# bluesky-api-client
This is Rest API client for BlueSky API.
Providing helper methods for authentication, fetching timelines, and managing content.

## Requirements
- Docker
- Docker Compose

## Usage
```bash
docker-compose up -d
```

## API

### GET
- [x] [/author-feed](https://atproto.com/lexicons/app-bsky-feed#appbskyfeedgetauthorfeed)
- [x] [/timeline](https://atproto.com/lexicons/app-bsky-feed#appbskyfeedgettimeline)
- [x] [/likes](https://atproto.com/lexicons/app-bsky-feed#appbskyfeedgetlikes)
- [ ] [/post-thread](https://atproto.com/lexicons/app-bsky-feed#appbskyfeedgetpostthread)

### POST
- [x] [/post](https://atproto.com/lexicons/com-atproto-repo#comatprotorepocreaterecord)
- [x] /login

### WIP
Support Twitter and BlueSky API
