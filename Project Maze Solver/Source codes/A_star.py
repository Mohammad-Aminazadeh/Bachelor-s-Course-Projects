board_size = 20
explored_set = []
expanded_nodes = []

def build_board ():
    board = []
    with open("D:\\UNI\\AI\\Project\\MAZE TC\\TestCase3.txt") as board_text:
        for item in board_text.readlines():
            board.append(item.rstrip().rsplit())
    board.reverse()
    return board

#finds the initial agent node on the board
def get_agent_node(board):
    agent_node = []
    for i in range(board_size):
        for j in range(board_size):
            if board[i][j] == "X":
                agent_node.append(i)
                agent_node.append(j)
                break
    return agent_node

def half_update_agent_node(board, moves, agent_node):
    for move in moves[:-1]:
        if move == "R":
            agent_node[1] = agent_node[1] + 1
        elif move == "L":
            agent_node[1] = agent_node[1] - 1
        elif move == "U":
            agent_node[0] = agent_node[0] + 1
        else:
            agent_node[0] = agent_node[0] - 1

def full_update_agent_node(board, moves, agent_node):
    for move in moves[:]:
        if move == "R":
            agent_node[1] = agent_node[1] + 1
        elif move == "L":
            agent_node[1] = agent_node[1] - 1
        elif move == "U":
            agent_node[0] = agent_node[0] + 1
        else:
            agent_node[0] = agent_node[0] - 1

def is_valid(board, moves):
    agent_node = get_agent_node(board)
    half_update_agent_node(board, moves, agent_node)
    if len(moves) == 0:
        return True
    if moves[-1] == "R":
        if agent_node[1] + 1 > board_size - 1 or board[agent_node[0]][agent_node[1] + 1] == "#":
            return False
        return True
    elif moves[-1] == "L":
        if agent_node[1] - 1 < 0 or board[agent_node[0]][agent_node[1] - 1] == "#":
            return False
        return True
    elif moves[-1] == "U":
        if agent_node[0] + 1 > board_size - 1 or board[agent_node[0] + 1][agent_node[1]] == "#":
            return False
        return True
    else:
        if agent_node[0] - 1 < 0 or board[agent_node[0] - 1][agent_node[1]] == "#":
            return False
        return True

def add_to_explored_set(board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)
    explored_set.append(agent_node)

def add_to_expanded_nodes(board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)
    expanded_nodes.append(agent_node)

def not_in_explored_set(board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)

    if agent_node not in explored_set:
        return True
    return False

def goal_test (board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves, agent_node)
    if board[agent_node[0]][agent_node[1]] == "O":
        return True
    return False

def manhattan_distance(firs_node, second_node):
    return abs(firs_node[0] - second_node[0]) + abs(firs_node[1] - second_node[1])

def get_goal_node(board):
    goal_node = []
    for i in range(board_size):
        for j in range(board_size):
            if board[i][j] == "O":
                goal_node.append(i)
                goal_node.append(j)
    return goal_node

#the distance of one node on the board to the goal node
def goal_distance(board, moves):
    node = map_moves_to_node(board, moves)
    goal_node = get_goal_node(board)
    return manhattan_distance(node, goal_node)

#the cost of a node from agent node
def distance_cost(board, moves):
    return len(moves)
    # node = map_moves_to_node(board, moves)
    # return manhattan_distance(get_goal_node(board), node)

def map_moves_to_node(board, moves):
    agent_node = get_agent_node(board)
    full_update_agent_node(board, moves,agent_node)
    return agent_node

def map_moves_to_nodes(board, moves):
    path_nodes = []
    path_nodes.append(get_agent_node(board))
    for move in moves:
        if move == "R":
            path_nodes.append([path_nodes[-1][0], path_nodes[-1][1] + 1])
        elif move == "L":
            path_nodes.append([path_nodes[-1][0], path_nodes[-1][1] - 1])
        elif move == "U":
            path_nodes.append([path_nodes[-1][0] + 1, path_nodes[-1][1]])
        else:
            path_nodes.append([path_nodes[-1][0] - 1, path_nodes[-1][1]])
    return path_nodes

def heuristic(board, moves):
    return goal_distance(board, moves) + distance_cost(board, moves)

def a_star(board):
    best_node = ""
    current_nodes = []
    temp_node = ""
    add_to_explored_set(board, get_agent_node(board))
    #adding initial moves
    for action in ["R", "L", "U", "D"]:
        temp_node = ""
        temp_node = temp_node + action
        if is_valid(board, temp_node):
            current_nodes.append(temp_node)
    while True:
        best_node = current_nodes[0]
        for node in current_nodes:
            if heuristic(board, node) < heuristic(board, best_node):
                best_node = node
        if goal_test(board, best_node):
            break
        current_nodes.remove(best_node)
        for action in ["R", "L", "U", "D"]:
            temp_node = best_node + action
            if is_valid(board, temp_node) and not_in_explored_set(board, temp_node):
                current_nodes.append(temp_node)
                add_to_explored_set(board, temp_node)
                add_to_expanded_nodes(board, temp_node)
    return best_node

if __name__ == "__main__":
    maze_board = build_board()
    solution = a_star(maze_board)
    print(f"The solution action sequence is {solution} and the cost is {len(solution)}")
    print(f"The path nodes are:\n{map_moves_to_nodes(maze_board, solution)}")
    print(f"The number of expanded nodes is:{len(expanded_nodes)}\n{expanded_nodes}")