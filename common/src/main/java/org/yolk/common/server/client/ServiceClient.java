package org.yolk.common.server.client;

/**
 * @author Liang Chenye
 * @version $Id: TaskClient, v 0.1 2015/8/13 14:32
 */

public class ServiceClient<T> {

    private T service;

    private ServiceClientInfo serviceClientInfo;

    public T getService() {
        return service;
    }

    public void setService(T service) {
        this.service = service;
    }

    public ServiceClientInfo getServiceClientInfo() {
        return serviceClientInfo;
    }

    public void setServiceClientInfo(ServiceClientInfo serviceClientInfo) {
        this.serviceClientInfo = serviceClientInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ServiceClient that = (ServiceClient) o;

        if (service != null ? !service.equals(that.service) : that.service != null)
            return false;
        return !(serviceClientInfo != null ?
                !serviceClientInfo.equals(that.serviceClientInfo) :
                that.serviceClientInfo != null);

    }

    @Override
    public int hashCode() {
        int result = service != null ? service.hashCode() : 0;
        result = 31 * result + (serviceClientInfo != null ? serviceClientInfo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("TaskClient, {");
        sb.append("service=");
        sb.append(service);
        sb.append(", taskServiceInfo=");
        sb.append(serviceClientInfo);
        sb.append('}');
        return sb.toString();
    }
}

