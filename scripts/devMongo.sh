function start() {
  docker run --name contentdb --rm -d -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=admin -p27017:27017 mongo:3.6
}

function stop() {
  docker stop contentdb
}

case $1 in
start)
  start
  ;;
stop)
  stop
  ;;
*)
  echo "Usage: $0 [ start | stop ]"
  exit 1
  ;;
esac