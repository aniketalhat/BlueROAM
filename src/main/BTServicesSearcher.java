package main;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.bluetooth.DataElement;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

import ui.MyDialog;

public class BTServicesSearcher {

	private static MyDialog dialog;
	static final UUID OBEX_OBJECT_PUSH = new UUID(0x1105);
	private static RemoteDeviceDiscoverer remoteDeviceDiscoverer;
	
    public static final Vector serviceFound = new Vector();

    static String tempDeviceName;
    
    public static void main(String[] args) throws IOException, InterruptedException {
		 new BTServicesSearcher(null);
//		try {
//			bts.function(null);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}		
	}

    public BTServicesSearcher(String args[]) throws IOException, InterruptedException {
    	
    	dialog = new MyDialog("BlueROAM",null, new DevicesDialogListener());
    	// 1st = setTitle();
    	// 2nd = array of Object
    	// 3rd = DevicesDialogListener class 
    	remoteDeviceDiscoverer = new RemoteDeviceDiscoverer();
    	
        // First run RemoteDeviceDiscovery and use discovered device
    	dialog.setStatus("Searching devices, please wait...");
    	remoteDeviceDiscoverer.searchDevices();
    	dialog.Pro();
        serviceFound.clear();
        
        UUID serviceUUID = OBEX_OBJECT_PUSH;
        if ((args != null) && (args.length > 0)) {
            serviceUUID = new UUID(args[0], false);
        }

        final Object serviceSearchCompletedEvent = new Object();

        DiscoveryListener listener = new DiscoveryListener() {

            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
            }

            public void inquiryCompleted(int discType) {
            }

            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            	
            	
                for (int i = 0; i < servRecord.length; i++) {
                	
                    String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                    if (url == null) {
                        continue;
                    }
                    serviceFound.add(url);
                    DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
                    if (serviceName != null) {
                        System.out.println("service " + serviceName.getValue() + " found " + url);
                    } else {
                        System.out.println("service found " + url);
                    }
                    
                    dialog.addItem(new BTDevice(tempDeviceName,url));
                }
            }

            public void serviceSearchCompleted(int transID, int respCode) {
                System.out.println("service search completed!");
                synchronized(serviceSearchCompletedEvent){
                    serviceSearchCompletedEvent.notifyAll();
                }
            }

        };

        UUID[] searchUuidSet = new UUID[] { serviceUUID };
        int[] attrIDs =  new int[] {
                0x0100 // Service name
        };

        int size = remoteDeviceDiscoverer.getDevicesDiscovered().size();
        dialog.setStatus(size + " devices found. Now checking which is supported.");
        for(Enumeration en = remoteDeviceDiscoverer.getDevicesDiscovered().elements(); en.hasMoreElements(); ) {
            RemoteDevice btDevice = (RemoteDevice)en.nextElement();

            synchronized(serviceSearchCompletedEvent) {
                String friendlyName = "";
                try{
	                friendlyName = btDevice.getFriendlyName(false);
	                tempDeviceName = friendlyName;
                }
                catch (Exception e) {
                    System.out.println("(dont know the name)");
                    tempDeviceName = null;
                }
                System.out.println("search services on " + btDevice.getBluetoothAddress() + " " + friendlyName);
                
                LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
                serviceSearchCompletedEvent.wait();
            }
        }
        
        dialog.setStatus("Search finished. Send a message");
        
    }
public BTServicesSearcher(boolean flag) {
		
		if(flag)
		{
			//new Progress();
			dialog.show();
		}
		
	}



}
