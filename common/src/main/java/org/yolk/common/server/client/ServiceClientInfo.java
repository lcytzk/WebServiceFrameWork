package org.yolk.common.server.client;

/**
 * @author Liang Chenye
 * @version $Id: TaskServiceInfo, v 0.1 2015/8/13 10:21
 */

public class ServiceClientInfo {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ServiceClientInfo that = (ServiceClientInfo) o;

        return !(address != null ? !address.equals(that.address) : that.address != null);
    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TaskServiceInfo, {");
        sb.append("address=");
        sb.append(address);
        sb.append('}');
        return sb.toString();
    }
}
