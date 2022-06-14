package com.example.owl.services;

import com.example.owl.dtos.ProbabilityDTO;
import org.springframework.stereotype.Service;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PointThreeService {
    private final ProbabilisticNetwork net;

    public PointThreeService() throws IOException {
        String filePath = "..\\bayes\\errors2.net";
        BaseIO io = new NetIO();
        net = (ProbabilisticNetwork) io.load(new File(filePath));
    }
    private void compile() {
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();
    }

    public void test() throws Exception {
        setFactNode("DysfunctionalUSBPort");
        List<Node> nodeList = net.getNodes();
        for (Node selected: nodeList) {
            if (Objects.equals(selected.getName(), "DysfunctionalUSBPort")) {
                System.out.println(selected.getName());
                for(Node parent : selected.getParents()){
                    System.out.println("    " + parent.getName());
                    for (int i = 0; i < parent.getStatesSize(); i++) {
                        System.out.println("        " + parent.getStateAt(i) + ": " + ((ProbabilisticNode) parent).getMarginalAt(i));
                    }
                }
            }
        }
    }

    private void setFactNode(String nodeName) throws Exception {
        compile();
        ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(nodeName);
        int stateIndex = 0; // index of state "yes"
        factNode.addFinding(stateIndex);
        net.updateEvidences();
    }

    public List<ProbabilityDTO> getAllProbabilities(String nodeName) throws Exception {
        setFactNode(nodeName);
        List<ProbabilityDTO> retVal = new ArrayList<>();
        for (Node parent : net.getNode(nodeName).getParents())
                retVal.add(makeProbabilityDTO(parent.getName(), ((ProbabilisticNode) parent).getMarginalAt(0)));
        return retVal;
    }

    private ProbabilityDTO makeProbabilityDTO(String name, float probability) {
        return new ProbabilityDTO(name, Math.round(probability * 100));
    }

    public void printProbabilities(String nodeName) throws Exception {
        for (ProbabilityDTO dto : getAllProbabilities(nodeName))
            System.out.println("Name: " + dto.getNodeName() + "; Probability: " + dto.getProbability());
    }


}
