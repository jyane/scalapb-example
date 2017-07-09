const grpc = require('grpc');

const PROTO_PATH = __dirname + '/../src/main/protobuf/helloworld.proto';
const HelloProto = grpc.load(PROTO_PATH).jp.jyane.hello.proto;

const main = () => {
  const client = new HelloProto.Greeter('localhost:11111', grpc.credentials.createInsecure());
  const name = process.argv.length >= 3 ? process.argv[2] : 'world';

  client.sayHello({ name: i + '' }, (err, res) => {
    console.log('Greeting:', res.message);
  });
}

main();
