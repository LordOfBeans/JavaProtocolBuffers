### Compiling Java Code

To compile the Java code, first navigate to the project's root directory. If you don't have a `com` directory, run `mkdir com`. Then run:

`
javac -d ./com -cp ./lib/protobuf.jar ./src/*.java
`
### Running the Echo Server

To run the echo server, open a terminal and navigate to the project's root directory. Then run:

`java -cp ./com:./lib/protobuf.jar EchoServer`

### Running the Client CLI

To run the client CLI, open a second terminal and navigate to the project's root directory. Then run:

`java -cp ./com:./lib/protobuf.jar EchoClient localhost`

### Optional: Compiling Protocol Buffer Source Code to Java

If you want to compile Protocol Buffer source code, you'll first have to download the Protocol Buffer compiler. It's available at the [Protocol Buffer Github](https://github.com/protocolbuffers/protobuf/releases). After you've downloaded the compiler and added it to your path, enter the project's root directory. Then run:

`protoc -I=./src --java_out=./src ./src/proto.proto`
