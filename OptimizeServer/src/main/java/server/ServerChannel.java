package server;
import java.io.IOException;
import java.util.concurrent.Executors;

import com.message.QueryProcessor;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ServerChannel {
	int serverNum;
	
	public ServerChannel(int p) {
		serverNum = p;
	}
	private static Server server;
	//static ExecutorService executor = Executors.newFixedThreadPool(20, new DefaultThreadFactory("grpc-server-test"));
	

	public static void main(String[] args) {
		 try {
             startServer("user", 9090);
         } catch (IOException | InterruptedException e) {
             e.printStackTrace();
         }
		
	}
	  private static void startServer(String name, int port) throws IOException, InterruptedException {
	    	 try {
				var serverBuilder = configureExecutor(ServerBuilder.forPort(port));
				 server = serverBuilder.addService(
						 new QueryProcessor()).maxInboundMessageSize(1024*1024*1024).build();
				 server.start();
				 
				 System.out.println("server started at "+ server.getPort());
			        server.awaitTermination();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	    }
	    
	    private static ServerBuilder<?> configureExecutor(ServerBuilder<?> sb) {
	        var threads = System.getenv("JVM_EXECUTOR_THREADS");
	        var i_threads = Runtime.getRuntime().availableProcessors();
	        if (threads != null && !threads.isEmpty()) {
	          i_threads = Integer.parseInt(threads);
	        }

	        /*
	         * Use a Direct Executor by default (best performance) since the GRPC
	         * service in this code is guaranteed non-blocking
	         */
	        var value = System.getenv().getOrDefault("JVM_EXECUTOR_TYPE", "direct");
	        switch (value) {
	          case "direct": sb = sb.directExecutor();
	          case "single": sb = sb.executor(Executors.newSingleThreadExecutor());
	          case "fixed": sb = sb.executor(Executors.newFixedThreadPool(i_threads));
	          case "workStealing": sb = sb.executor(Executors.newWorkStealingPool(2*i_threads));
	          case "cached": sb = sb.executor(Executors.newCachedThreadPool());
	        }

	        return sb;
	      }

}
